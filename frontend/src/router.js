import Vue from 'vue'
import Router from 'vue-router'
import LoginForm from './components/LoginForm.vue'
import Blogs from './components/BlogList.vue'
import Posts from './components/PostList.vue'

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
      component: LoginForm
    },
    {
      path: '/new-blog',
      name: 'BlogForm',
      component: LoginForm
    },
    {
      path: '/new-post',
      name: 'PostForm',
      component: LoginForm
    }
  ]
})
