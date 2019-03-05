<template>
  <div>
    <div v-for="blog in blogs" :key="blog.id">
      <div>Nome:
        <router-link to="/">{{blog.name}}</router-link>
      </div>
      
      <div>Descrição: {{blog.title}}</div>
      <br>
    </div>
    <div v-if="blogs && blogs.length === 0">
      <span>Nenhum blog encontrado =(</span>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    blogs: []
  }),
  mounted() {
    fetch("http://192.168.0.117:8080/blogs")
      .then(response => response.json())
      .then(blogs => (this.blogs = blogs));
  }
};
</script>

<style>
</style>