<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
      | <router-link v-if="user" to="/new-blog">New Blog</router-link>
        <router-link v-else to="/new-user">New User</router-link>
      | <router-link v-if="!user" to="/login">Login</router-link>
        <span v-else>
          Logged as {{user}} <a class="pointer" @click.prevent="logout">(Logout)</a>
        </span>
    </div>
    <br /><br/>
    <router-view/>
  </div>
</template>

<script>
export default {
    data: () => ({
      user: null
    }),
    mounted() {
      this.user = localStorage.getItem('user.name')
    },
    methods: {
      logout() {
        localStorage.clear()
        this.user = null
      }
    }
}
</script>

<style>
#app {
  color: #2c3e50;
}
.pointer {
  cursor: pointer;
}
</style>
