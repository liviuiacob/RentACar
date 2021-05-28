import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    userId: null,
    isLogged: false,
    admin: null,
  },
  getters: {

    isLogged: (state) => {
      return state.isLogged;
    },
    isAdmin: (state) => {
      return state.admin;
    },
    loggedUser: (state) => {
      return state.userId;
    },

  },
  mutations: {
    setUserID(state, userId) {
      state.userId = userId;
    },
    setIsLogged(state, isLogged) {
      state.isLogged = isLogged;
    },
    setAdmin(state, admin) {
      state.admin = admin;
    }
  },
  actions: {
    logID(context, userId) {
      context.commit('setUserID', userId);
    },
    login(context, loginInfo) {
      context.commit('setUserID', loginInfo.userId);
      context.commit('setIsLogged', true);
      context.commit('setAdmin', loginInfo.admin);
    },
    logout(context) {
      context.commit('setUserID', null);
      context.commit('setIsLogged', false);
      context.commit('setAdmin', null);
    }
  },
  modules: {
  }
})
