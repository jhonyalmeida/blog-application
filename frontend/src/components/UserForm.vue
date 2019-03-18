<template>
  <div>
    <form @submit.prevent="onSubmit">
        <div>
            <span v-show="error">{{ error }}</span>
        </div>
        <div>
            <label for="username">Username:</label>
            <input id="username" type="text" v-model="username" />
        </div>
        <div>
            <label for="password">Password:</label>
            <input id="password" type="password" v-model="password" />
        </div>
        <div>
            <button type="submit">Salvar</button>
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