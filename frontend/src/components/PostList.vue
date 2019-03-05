<template>
  <div>
    Posts do blog {{blogId}}
  </div>
</template>

<script>
export default {
  data: () => ({
    posts: []
  }),
  props: {
    blogId: String
  },
  mounted() {
    fetch(`http://localhost:8081/blogs/${this.blogId}/posts`)
      .then(response => response.json())
      .then(posts => (this.posts = posts));
  }
};
</script>

<style>
</style>