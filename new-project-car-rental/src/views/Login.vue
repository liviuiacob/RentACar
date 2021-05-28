<template>
  <div class="Login">
    <v-container>
      <v-form ref="form" v-model="valid" lazy-validation>
          <v-row>
            <v-col cols="8" lg="4" md="6" xs="12">
              <v-text-field
                v-model="username"
                :counter="10"
                :rules="usernameRules"
                label="Username"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="8" lg="4" md="6" xs="12">
              <v-text-field
                type="password"
                v-model="password"
                :rules="passwordRules"
                label="Password"
                required
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" lg="4" md="6" xs="12">
              <v-btn rounded color="primary" dark @click="login"> Login </v-btn>
            </v-col>
          </v-row>
      </v-form>
    </v-container>
  </div>
</template>


<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      valid: false,
      usernameRules: [
        (v) => !!v || "Userame is required",
        (v) =>
          (v && v.length <= 10) || "Userame must be less than 10 characters",
      ],
      passwordRules: [(v) => !!v || "Password is required"],
      username: "",
      password: "",
    };
  },
  methods: {
    login() {
      this.$refs.form.validate();
      if (this.valid) {
        axios
          .post("http://localhost:8080/api/Users/login", {
            username: this.username,
            password: this.password,
          })
          .then((response) => {
            if (response.data == 1) {
              this.$store.dispatch("login", {
                userId: response.data,
                admin: false,
              });
              axios.get(`http://localhost:8080/api/Users/get/${this.username}`)
            .then((response) => {
              this.$store.dispatch("logID", response.data);
            })
            .catch(()=>{});
              this.$router.push({ name: 'Cars' });
            }
            if (response.data == 2) {
              this.$store.dispatch("login", {
                userId: response.data,
                admin: true,
              });
              axios.get(`http://localhost:8080/api/Users/get/${this.username}`)
            .then((response) => {
              this.$store.dispatch("logID", response.data);
            })
            .catch(()=>{});
              this.$router.push({ name: 'Cars' });
            }

            
          })
          .catch(() => {
            alert("invalid credentials");
          });
          
      }
    },
  },
};
</script>

