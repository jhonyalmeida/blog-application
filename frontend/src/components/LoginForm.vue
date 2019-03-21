<template>
  <div class="container">
    <div class="alert alert-danger" role="alert" v-show="error">{{ error }}</div>
    <form @submit.prevent="onSubmit">
      <div class="form-group">
        <label for="username">Username:</label>
        <input class="form-control" id="username" type="text" v-model="username" />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input class="form-control" id="password" type="password" v-model="password" />
      </div>
      <br />
      <div class="form-group">
        <button class="btn btn-primary" type="submit">Acessar</button>
      </div>
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