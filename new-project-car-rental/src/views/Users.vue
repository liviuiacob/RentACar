<template>
  <div class="users">
    <v-col>
    <h1><v-btn v-if="isAdmin" rounded color="primary" dark href="/AddUser"> Add a new user </v-btn></h1>
    </v-col>
    <v-form>
      <v-container v-if="isAdmin">
        <v-row>
          <v-spacer></v-spacer>
          <v-col cols="7">
            <v-row>
              <v-col
                cols="6"
                lg="4"
                md="6"
                xs="12"
                v-for="user in users"
                :key="user.id"
              >
                <v-card >
                  <v-img
                    src="http://2.bp.blogspot.com/-HzFJhEY3KtU/Tea7Ku92cpI/AAAAAAAAALw/uBMzwdFi_kA/s1600/1.jpg"
                    height="200px"
                  ></v-img>

                  <v-card-title> Username: {{ user.username }} </v-card-title>

                  <v-card-subtitle> Admin: {{ user.admin }} </v-card-subtitle>

                  <v-card-actions>
                    <v-btn color="orange lighten-2" text @click="selectUser(user)"> <v-icon dark>mdi-pencil</v-icon> </v-btn>

                    <v-spacer></v-spacer>

                    <v-btn color="orange lighten-2" text @click.stop="deleteUser(user)"> <v-icon dark>mdi-delete</v-icon> </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="6"
                lg="4"
                md="6"
                xs="12">
            <v-card outlined v-if="selectedUser !== null">
                <v-card-title> Update user </v-card-title>
                <v-card-text>
                    <v-text-field v-model="selectedUser.username" label="Username" placeholder="String"></v-text-field>
                    <v-text-field v-model="selectedUser.password" label="Password" placeholder="String"></v-text-field>
                    <v-checkbox v-model="selectedUser.admin" :label="`Admin: ${selectedUser.admin}`"></v-checkbox>
                </v-card-text>
            <v-card-actions>
              <v-btn rounded color="primary" dark  @click="updateUser"> Save changes </v-btn>
              <v-spacer></v-spacer>
              <v-btn rounded color="primary" dark  @click="selectedUser=null"> Close </v-btn>
              
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
import lodash from 'lodash';
import { mapGetters } from "vuex";

export default {
  name: "Users",
  data() {
    return {
      users: [],
      selectedUser: null,
    };
  },
  created() {
    this.loadUsers();
  },
  methods: {
    loadUsers() {
      axios
        .get("http://localhost:8080/api/Users")
        .then((response) => {
          this.users = response.data;
        })
        .catch((err) => {});
    },
    selectUser(user) {
      this.selectedUser = lodash.cloneDeep(user);
    },
    updateUser() {
      axios
        .put(
          `http://localhost:8080/api/Users/${this.selectedUser.id}`,
          this.selectedUser
        )
        .then((response) => {
          this.loadUsers();
        })
        .catch((err) => {});
    },
    deleteUser(user) {
      axios
        .delete(`http://localhost:8080/api/Users/${user.id}`)
        .then((response) => {
          this.loadUsers();
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
