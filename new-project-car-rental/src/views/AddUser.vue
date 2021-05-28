<template>
  <div class="AddUser">
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="8" lg="4" md="6" xs="12">
            <v-card outlined>
              <v-card-title> Add a user </v-card-title>
              <v-card-text>
                <v-text-field
                  v-model="user.username"
                  label="Username"
                  placeholder="String"
                ></v-text-field>
                <v-text-field
                  v-model="user.password"
                  label="Password"
                  placeholder="String"
                ></v-text-field>
                <v-checkbox
                  v-if="isAdmin"
                  v-model="user.admin"
                  :label="`Admin: ${user.admin}`"
                ></v-checkbox>
              </v-card-text>
              <v-card-actions>
                <v-btn
                  rounded
                  color="primary"
                  dark
                  v-if="isAdmin"
                  href="/Users"
                  @click="saveUser"
                >
                  Save User
                </v-btn>
                 <v-btn
                  rounded
                  color="primary"
                  dark
                  v-if="!isAdmin"
                  href="/"
                  @click="saveUser"
                >
                  Save User
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>

          <v-col cols="8">
            <v-col cols="12" lg="4" md="6" xs="12">
              <v-card>
                <v-img
                  src="http://2.bp.blogspot.com/-HzFJhEY3KtU/Tea7Ku92cpI/AAAAAAAAALw/uBMzwdFi_kA/s1600/1.jpg"
                  height="200px"
                ></v-img>

                <v-card-title> Username: {{ user.username }} </v-card-title>

                <v-card-subtitle>
                  Password: {{ user.password }}
                </v-card-subtitle>

                <v-card-subtitle> Admin: {{ user.admin }} </v-card-subtitle>
              </v-card>
            </v-col>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "AddUser",
  data() {
    return {
      user: {
        username: "",
        password: "",
        admin: false,
      },
    };
  },
  methods: {
    saveUser() {
      axios
        .post("http://localhost:8080/api/Users", this.user)
        .then(() => {})
        .catch((err) => {});
    },
  },
  computed: {
    ...mapGetters(["isLogged"]),
    ...mapGetters(["isAdmin"]),
  },
};
</script>

