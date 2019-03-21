<template>
  <div class="container">
    <form @submit.prevent="onSubmit">
        <div class="alert alert-danger" role="alert" v-show="error">{{ error }}</div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input class="form-control" id="name" type="text" v-model="name" />
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" v-model="description" />
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
    name: '',
    description: '',
    error: ''
  }),
  methods: {
    onSubmit() {
        fetch(`http://localhost:8081/blogs`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                name: this.name,
                description: this.description,
                userId: localStorage.getItem('user.token')
            })
        })
        .then(response => response.ok ? response.json() : Promise.reject())
        .then(blog => {
            this.name = ''
            this.description = ''
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