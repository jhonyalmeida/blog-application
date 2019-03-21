<template>
  <div class="container">
    <form @submit.prevent="onSubmit">
        <div class="alert alert-danger" role="alert" v-show="error">{{ error }}</div>
        <div class="row">
            <label for="title">Title:</label>
            <input class="form-control" id="title" type="text" v-model="rootSection.title" />
        </div>
        <div class="row">
            <label for="content">Content:</label>
            <textarea class="form-control" id="content" v-model="rootSection.content" />
        </div>
        <div>
            <p>
                <a class="pointer" @click="addSection">+ Add Section</a>
            </p>
            <subsection-form v-for="(section, index) in rootSection.sections" 
                    :key="index"
                    v-model="rootSection.sections[index]"
                    :number="`${index+1}`" />
        </div>
        <br />
        <div class="row">
            <button class="btn btn-primary" type="submit">Salvar</button>
        </div>
    </form>
  </div>
</template>

<script>
import SubsectionForm from './SubsectionForm'

export default {
    components: { SubsectionForm },
    data: () => ({
        rootSection: {
            title: '',
            content: '',
            sections: []
        },
        error: ''
    }),
    props: {
        blogId: String
    },
    methods: {
        addSection() {
            this.rootSection.sections = [ 
                ...this.rootSection.sections, 
                { title: '', content: '' }
            ]
        },
        onSubmit() {
            fetch(`http://localhost:8081/posts`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    rootSection: this.rootSection,
                    blogId: this.blogId
                })
            })
            .then(response => response.ok ? response.json() : Promise.reject())
            .then(post => {
                this.$router.push(`/blogs/${this.blogId}/posts`)
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