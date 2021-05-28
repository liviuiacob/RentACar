<template>
  <div class="istoric">
    <v-form>
      <v-container v-if="isAdmin">
        <v-row>
          <v-col cols="7">
            <v-row>
              <v-col
                cols="6"
                lg="4"
                md="6"
                xs="12"
                v-for="rental in rentals"
                :key="rental.id"
              >
                <v-card>
                  <v-card-title>
                    Username: {{ rental.user }}
                  </v-card-title>

                  <v-card-title>
                    Car: {{ rental.car }}
                  </v-card-title>

                  <v-card-subtitle>
                    from: {{ rental.from }}
                  </v-card-subtitle>
                  <v-card-subtitle>
                    to: {{ rental.to }}
                  </v-card-subtitle>
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
  name: "Users",
  data() {
    return {
      rentals: [],
      car: String (""),
      user: String (""),
    };
  },
  created() {
    this.loadUsers();
  },
  methods: {
    loadUsers() {
      axios
        .get("http://localhost:8080/api/Rentals/merge")
        .then((response) => {
          this.rentals = response.data;
        })
        .catch((err) => {});
        console.log(this.rentals);
    },
  },
  computed: {
    ...mapGetters(["isLogged"]),
    ...mapGetters(["isAdmin"]),
  },
};
</script>
