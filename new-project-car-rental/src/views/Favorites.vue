<template>
  <div class="Favorites">
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="7">
            <v-row>
              <v-col
                cols="6"
                lg="4"
                md="6"
                xs="12"
                v-for="car in user.fav"
                :key="car.id"
              >
                <v-card>
                  <v-img :src="car.car.coverURL" height="200px"></v-img>

                  <v-card-title> {{ car.car.name }} </v-card-title>

                  <v-card-subtitle>
                    yearOfFabrication: {{ car.car.yearOfFabrication }}
                  </v-card-subtitle>

                  <v-card-subtitle>
                    Price/Day: {{ car.car.price }}
                  </v-card-subtitle>

                  <v-card-actions>
                    <v-btn
                      color="orange lighten-2"
                      text
                      target="_blank"
                      :href="car.car.coverURL"
                      ><v-icon dark>mdi-eye</v-icon>
                    </v-btn>

                    <v-spacer></v-spacer>

                    <v-btn
                      color="orange lighten-2"
                      text
                      @click.stop="deleteCar(car.car)"
                    >
                      <v-icon dark>mdi-heart</v-icon>
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
            </v-row>
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

export default {
  name: "Cars",
  data() {
    return {
      cars: [],
      user:{
        id:null, 
        username:"",
        password:"",
        admin:null,
        fav:[],
        rentals:[],
      },
    };
  },
  created() {
    this.loadFavorites();
  },
  methods: {
    loadFavorites() {
      axios
        .get(`http://localhost:8080/api/Users/${this.$store.state.userId}`)
        .then((response) => {
          this.user = response.data;
        })
        .catch((err) => {});
    },
    deleteCar(car) {
      console.log(car.id);
      axios
        .put(`http://localhost:8080/api/Users/remove/${this.$store.state.userId}`, {userId:this.$store.state.userId, carId:car.id})
        .then((response) => {
          this.loadFavorites();
        })
        .catch((err) => {});
    },
  },
  computed: {
    ...mapGetters(["isLogged"]),
    ...mapGetters(["isAdmin"]),
  },
};
</script>
