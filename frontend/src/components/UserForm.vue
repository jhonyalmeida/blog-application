<template>
  <div class="container">
    <form @submit.prevent="onSubmit">
        <div class="alert alert-danger" role="alert" v-show="error">{{ error }}</div>
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
            <button class="btn btn-primary" type="submit">Salvar</button>
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
        fetch(`http://localhost:8081/users`, {
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
            this.username = ''
            this.password = ''
            this.error = ''
        })
        .catch(error => {
            this.error = 'An error ocurred, check if all mandatory fields are filled'
        })
    }
  }
};
</script>

<style>
</style>