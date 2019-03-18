<template>
  <div>
    <form @submit.prevent="onSubmit">
        <div>
            <span v-show="error">{{ error }}</span>
        </div>
        <div>
            <label for="name">Name:</label>
            <input id="name" type="text" v-model="name" />
        </div>
        <div>
            <label for="description">Description:</label>
            <textarea id="description" v-model="description" />
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