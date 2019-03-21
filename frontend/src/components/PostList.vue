<template>
    <div class="container">
        <h1 v-if="blog">{{ blog.name }}</h1>
        <div v-if="isOwner">
          <router-link :to="`/blogs/${blogId}/new-post`">New Post</router-link>
          <br /><br />
        </div>
        <div class="card mb-3" v-for="post in posts" :key="post._id">
          <div class="card-body pb-0">
            <h5 class="card-title">{{ post.rootSection.title }}</h5>
            <em>{{ new Date(post.timestamp).toLocaleString() }}</em>
            <p class="card-text">{{post.rootSection.content}}</p>
            <subsection v-for="(section, index) in post.rootSection.sections" 
                :key="index" 
                :number="`${index+1}`"
                :section="section" />
          </div>
        </div>
        <div v-if="posts && posts.length === 0">
            <span>Nada foi publicado neste blog ainda =(</span>
        </div>
    </div>
</template>

<script>
import Subsection from './Subsection'

export default {
  components: { Subsection },
  data: () => ({
    blog: null,
    posts: []
  }),
  props: {
    blogId: String
  },
  computed: {
    isOwner: function () {
      return this.blog && this.blog.userId === localStorage.getItem('user.token')  
    }
  },
  mounted() {
    fetch(`http://localhost:8081/blogs/${this.blogId}`)
      .then(response => response.json())
      .then(blog => (this.blog = blog));
    
    fetch(`http://localhost:8081/blogs/${this.blogId}/posts`)
      .then(response => response.json())
      .then(posts => (this.posts = posts));
  }
};
</script>

<style>
</style>