<template>
  <div>
    <span v-show="error">{{error}}</span>
    <form @submit.prevent="onSubmit">
        <label for="username">Username:</label>
        <input id="username" type="text" v-model="username" />
        <label for="password">Password:</label>
        <input id="password" type="password" v-model="password" />
        <button type="submit">Acessar</button>
    </form>
  </div>
</template>

<script>
export default {
  data: () => ({
    username: '',
    password: '',
    error: ''
  }),
  methods: {
    onSubmit() {
        fetch(`http://localhost:8081/users/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                login: this.username,
                password: this.password
            })
        })
        .then(response => response.ok ? response.json() : Promise.reject())
        .then(user => {
            localStorage.setItem('user.token', user._id)
            localStorage.setItem('user.name', user.login)
            window.location.href = '/'
        })
        .catch(error => {
            this.error = 'Username or password incorrect'
        })
    }
  }
};
</script>

<style>
</style>