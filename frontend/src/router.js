import Vue from 'vue'
import Router from 'vue-router'
import Blogs from './components/BlogList.vue'
import Posts from './components/PostList.vue'

Vue.use(Router)

export default new Router({
  routes: [

    {
      path: '/',
      name: 'Blogs',
      component: Blogs
    } ,
    {
      path: '/blogs/:blogId/posts',
      name: 'Posts',
      component: Posts,
      props: true
    }
  ]
})
