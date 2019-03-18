<template>
  <div>
    <form @submit.prevent="onSubmit">
        <div>
            <span v-show="error">{{ error }}</span>
        </div>
        <div>
            <label for="title">Title:</label>
            <input id="title" type="text" v-model="rootSection.title" />
        </div>
        <div>
            <label for="content">Content:</label>
            <textarea id="content" v-model="rootSection.content" />
        </div>
        <div>
            <a class="pointer" @click="addSection">+ Add Section</a>
            <subsection-form v-for="(section, index) in rootSection.sections" 
                    :key="index"
                    v-model="rootSection.sections[index]"
                    :number="`${index+1}`" />
        </div>
        <div>
            <button type="submit">Salvar</button>
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