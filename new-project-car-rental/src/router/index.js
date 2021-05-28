import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/Home',
    name: 'Home',
    component: Home
  },
  {
    path: '/Cars',
    name: 'Cars',
    component: function () {
      return import(/* webpackChunkName: "Cars" */ '../views/Cars.vue')
    }
  },
  {
    path: '/AddCar',
    name: 'AddCar',
    component: function () {
      return import(/* webpackChunkName: "AddCar" */ '../views/AddCar.vue')
    }
  },
  {
    path: '/',
    name: 'Login',
    component: function () {
      return import(/* webpackChunkName: "Login" */ '../views/Login.vue')
    }
  },
  {
    path: '/Istoric',
    name: 'Istoric',
    component: function () {
      return import(/* webpackChunkName: "Istoric" */ '../views/Istoric.vue')
    }
  },
  {
    path: '/Users',
    name: 'Users',
    component: function () {
      return import(/* webpackChunkName: "Users" */ '../views/Users.vue')
    }
  },
  {
    path: '/AddUser',
    name: 'AddUser',
    component: function () {
      return import(/* webpackChunkName: "AddUser" */ '../views/AddUser.vue')
    }
  },
  {
    path: '/Favorites',
    name: 'Favorites',
    component: function () {
      return import(/* webpackChunkName: "Favorites" */ '../views/Favorites.vue')
    }
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
