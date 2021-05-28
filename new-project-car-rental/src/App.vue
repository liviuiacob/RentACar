<template>
  <div id="app">
    <div id="nav">
     <v-btn icon :to="{ name: 'Home'}"> <v-icon>mdi-home</v-icon></v-btn> 
      <v-btn v-if="!isLogged" icon :to="{ name: 'AddUser'}"> <v-icon>mdi-account-plus</v-icon></v-btn> 
      <v-btn v-if="!isLogged" icon :to="{ name: 'Login'}"> <v-icon>mdi-account</v-icon></v-btn> 
      <v-btn v-if="!isAdmin && isLogged" icon :to="{ name: 'Favorites'}"> <v-icon>mdi-heart</v-icon></v-btn> 
      <v-btn v-if="isLogged" icon :to="{ name: 'Cars'}"> <v-icon>mdi-car</v-icon></v-btn>   
      <v-btn v-if="isAdmin" icon :to="{ name: 'Users'}"> <v-icon>mdi-account</v-icon></v-btn> 
      <v-btn v-if="isAdmin" icon :to="{ name: 'Istoric'}"> <v-icon>mdi-account-details</v-icon></v-btn> 
      <v-btn v-if="isLogged" icon @click="$store.dispatch('logout'); $router.push({name: 'Login'})"> <v-icon>mdi-logout</v-icon></v-btn> 

    </div>
    <!--<img
      src="data:image/png;base64,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"
    /> -->

    <router-view />
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>

<script>
import { mapGetters } from "vuex";

export default {
  name: "App",
  methods: {
    
  },
  computed: {
    ...mapGetters(["isLogged"]),
    ...mapGetters(["isAdmin"]),
  },
};
</script>
