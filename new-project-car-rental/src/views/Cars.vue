<template>
  <div class="Cars">
    <v-col>
      <h1>
        <v-btn v-if="isAdmin" rounded color="primary" dark href="/AddCar">
          Add a new Car
        </v-btn>
      </h1>
    </v-col>
    <v-container v-if="!isAdmin">
      <v-row>
        <v-col cols="6" lg="4" md="6" xs="12">
          <v-date-picker dark v-model="dates" range></v-date-picker>
        </v-col>
        <v-col cols="6" lg="4" md="6" xs="12">
          <v-text-field
            v-model="dateRangeText"
            label="Date range"
            prepend-icon="mdi-calendar"
            readonly
          ></v-text-field>
        </v-col>
      </v-row>
      <v-btn rounded color="primary" dark v-if="!isAdmin" @click="loadRentCars">
        Confirm
      </v-btn>
    </v-container>
    <v-form>
      <v-container>
        <v-row>
          <v-spacer></v-spacer>
          <v-col cols="7">
            <v-row>
              <v-col
                cols="6"
                lg="4"
                md="6"
                xs="12"
                v-for="car in cars"
                :key="car.id"
              >
                <v-card>
                  <v-img :src="car.coverURL" height="200px"></v-img>

                  <v-card-title> {{ car.name }} </v-card-title>

                  <v-card-subtitle>
                    yearOfFabrication: {{ car.yearOfFabrication }}
                  </v-card-subtitle>

                  <v-card-subtitle>
                    Price/Day: {{ car.price }}
                  </v-card-subtitle>

                  <v-card-actions>
                    <v-btn
                      v-if="isAdmin"
                      color="orange lighten-2"
                      text
                      @click="selectCar(car)"
                    >
                      <v-icon dark>mdi-wrench</v-icon>
                    </v-btn>
                    <v-btn
                      color="orange lighten-2"
                      text
                      target="_blank"
                      @click="generatePDF(car)"
                      ><v-icon dark>mdi-eye</v-icon>
                    </v-btn>

                    <v-btn
                      color="orange lighten-2"
                      text
                      @click="addFavorite(car)"
                      ><v-icon dark>mdi-heart</v-icon>
                    </v-btn>

                    <v-btn
                      color="orange lighten-2"
                      text
                      @click="rentCar(car)"
                      href="/Cars"
                      ><v-icon dark>mdi-account-cash</v-icon>
                    </v-btn>

                    <v-spacer></v-spacer>

                    <v-btn
                      v-if="isAdmin"
                      color="orange lighten-2"
                      text
                      @click.stop="deleteCar(car)"
                    >
                      <v-icon dark>mdi-delete</v-icon>
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="6" lg="4" md="6" xs="12">
            <v-card outlined v-if="selectedCar !== null">
              <v-card-title> Update Car </v-card-title>
              <v-card-text>
                <v-text-field
                  v-model="selectedCar.name"
                  label="Denumire"
                  placeholder="String"
                ></v-text-field>
                <v-text-field
                  v-model="selectedCar.yearOfFabrication"
                  label="Anul Fabricatiei"
                  placeholder="Int"
                ></v-text-field>
                <v-text-field
                  v-model="selectedCar.coverURL"
                  label="CoverURL"
                  placeholder="https://"
                ></v-text-field>
                <v-text-field
                  v-model="selectedCar.price"
                  label="Price/Day"
                  placeholder="Int $$$"
                ></v-text-field>
              </v-card-text>
              <v-card-actions>
                <v-btn rounded color="primary" dark @click="updateCar">
                  Save changes
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn rounded color="primary" dark @click="selectedCar = null">
                  Close
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import axios from "axios";
import lodash from "lodash";
import { mapGetters } from "vuex";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

export default {
  name: "Cars",
  data() {
    return {
      cars: [],
      selectedCar: null,
      dates: ["2019-09-10", "2019-09-10"],
      fromdate: new Date("2019-09-10"),
      todate: new Date("2019-09-10"),
      user: {
        id: null,
        username: "",
        password: "",
        admin: null,
        fav: [],
        rentals: [],
      },
      userId: this.loggedUser,
    };
  },
  created() {
    //console.log(this.$store.state.admin);
    if (this.$store.state.admin == true) this.loadCars();
    else this.loadRentCars();

    this.subscribeToNotifications();
  },
  methods: {
    loadCars() {
      this.cars = [];
      axios
        .get("http://localhost:8080/api/Cars")
        .then((response) => {
          this.cars = response.data;
        })
        .catch((err) => {});
    },
    loadRentCars() {
      //console.log(this.fromdate);

      this.fromdate = new Date(this.dates[0]);
      var date = this.fromdate.getDate();
      var month = this.fromdate.getMonth(); //Be careful! January is 0 not 1
      var year = this.fromdate.getFullYear();
      var dateString = date + "/" + (month + 1) + "/" + year;

      this.todate = new Date(this.dates[1]);
      var date1 = this.todate.getDate();
      var month1 = this.todate.getMonth();
      var year1 = this.todate.getFullYear();
      var dateString1 = date1 + "/" + (month1 + 1) + "/" + year1;
      console.log(dateString, dateString1);
      axios
        .post("http://localhost:8080/api/Rental/get", {
          fromdate: dateString,
          todate: dateString1,
        })
        .then((response) => {
          this.cars = response.data;
        })
        .catch((err) => {});
    },
    selectCar(car) {
      this.selectedCar = lodash.cloneDeep(car);
    },
    updateCar() {
      axios
        .put(
          `http://localhost:8080/api/Cars/${this.selectedCar.id}`,
          this.selectedCar
        )
        .then((response) => {
          this.loadCars();
        })
        .catch((err) => {});
    },
    deleteCar(car) {
      axios
        .delete(`http://localhost:8080/api/Cars/${car.id}`)
        .then((response) => {
          this.loadCars();
        })
        .catch((err) => {});
    },
    addFavorite(car) {
      axios
        .get(`http://localhost:8080/api/Users/${this.$store.state.userId}`)
        .then((response) => {
          this.user = response.data;
        })
        .catch((err) => {});
      this.user.fav.push(car);

      axios
        .put(
          `http://localhost:8080/api/Users/ceva/${this.$store.state.userId}`,
          { userId: this.$store.state.userId, carId: car.id }
        )
        .then((response) => {
          this.loadCars();
        })
        .catch((err) => {});
    },
    rentCar(car) {
      //console.log(this.$store.state.userId);
      ///*
      var date = this.fromdate.getDate();
      var month = this.fromdate.getMonth(); //Be careful! January is 0 not 1
      var year = this.fromdate.getFullYear();
      var dateString = date + "/" + (month + 1) + "/" + year;

      var date1 = this.todate.getDate();
      var month1 = this.todate.getMonth();
      var year1 = this.todate.getFullYear();
      var dateString1 = date1 + "/" + (month1 + 1) + "/" + year1;

      axios
        .put("http://localhost:8080/api/Rentals/update", {
          fromdate: dateString,
          todate: dateString1,
          carid: car.id,
          userid: this.$store.state.userId,
        })
        .then((response) => {
          this.loadCars();
        })
        .catch((err) => {});
      //*/
    },
    generatePDF(car) {
      axios
        .put(`http://localhost:8080/generatePdf/${car.id}`)
        .then((response) => {
          alert("PDF in " + response.data);
        });
    },
    subscribeToNotifications() {
      const URL = "http://localhost:8080/socket";
      const websocket = new SockJS(URL);
      const stompClient = Stomp.over(websocket);
      stompClient.connect({}, () => {
        stompClient.subscribe("/topic/socket/rental/", (notification) => {
          const message = notification.body;
          //alert("Some1 just rented a car, RELOADING PAGE");
          window.location.reload();

        });
      });
    },
  },
  computed: {
    ...mapGetters(["isLogged"]),
    ...mapGetters(["isAdmin"]),
    ...mapGetters(["loggedUser"]),
    dateRangeText() {
      return this.dates.join(" ~ ");
    },
  },
};
</script>
