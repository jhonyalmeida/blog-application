import Vue from 'vue'
import Router from 'vue-router'
import LoginForm from './components/LoginForm.vue'
import Blogs from './components/BlogList.vue'
import Posts from './components/PostList.vue'
import UserForm from './components/UserForm.vue'
import BlogForm from './components/BlogForm.vue'
import PostForm from './components/PostForm.vue'

Vue.use(Router)

export default new Router({
  routes: [

    {
      path: '/',
      name: 'Blogs',
      component: Blogs
    },
    {
      path: '/blogs/:blogId/posts',
      name: 'Posts',
      component: Posts,
      props: true
    },
    {
      path: '/login',
      name: 'Login',
      component: LoginForm
    },
    {
      path: '/new-user',
      name: 'UserForm',
      component: UserForm
    },
    {
      path: '/new-blog',
      name: 'BlogForm',
      component: BlogForm
    },
    {
      path: '/blogs/:blogId/new-post',
      name: 'PostForm',
      component: PostForm,
      props: true
    }
  ]
})
