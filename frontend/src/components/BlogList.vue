<template>
  <div class="container">
    <div class="card mb-3" v-for="blog in blogs" v-show="blog.name" :key="blog._id">
      <div class="card-body pb-0">
        <h5 class="card-title">
          <router-link :to="`/blogs/${blog._id}/posts`">{{ blog.name }}</router-link>
        </h5>
        <div class="card-text">{{ blog.description }}</div>
        <br />
        <div v-if="blog.lastPublished">
          <em>Última Publicação: {{ new Date(blog.lastPublished).toLocaleString() }}</em>
        </div>
        <br />
      </div>
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