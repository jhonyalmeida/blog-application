<template>
    <div>
        <h1 v-if="blog">{{blog.name}}</h1>
        <div v-for="post in posts" :key="post._id">
            <h2>{{post.rootSection.title}}</h2>
            <em>{{new Date(post.timestamp).toLocaleString() }}</em>
            <p>{{post.rootSection.content}}</p>
            <subsection v-for="(section, index) in post.rootSection.sections" 
                :key="index" 
                :number="`${index+1}`"
                :section="section" />
            <br />
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