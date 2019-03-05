<template>
  <div>
    <div v-for="blog in blogs" :key="blog._id">
      <div>Nome:
        <router-link :to="`/blogs/${blog._id}/posts`">{{blog.name}}</router-link>
      </div>

      <div>Descrição: {{blog.description}}</div>
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
    fetch("http://localhost:8081/blogs")
      .then(response => response.json())
      .then(blogs => (this.blogs = blogs));
  }
};
</script>

<style>
</style>