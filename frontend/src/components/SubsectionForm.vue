<template>
    <div class="container">
        <strong># Section {{ number }}</strong>
        <div v-if="section">
            <div>
                <label for="title">Title:</label>
                <input class="form-control" id="title" type="text" v-model="section.title" @input="handleInput" />
            </div>
            <div>
                <label for="content">Content:</label>
                <textarea class="form-control" id="content" v-model="section.content" @input="handleInput" />
            </div>
            <div>
                <p>
                    <a class="pointer" @click="addSubSection">+ Add SubSection</a>
                </p>
                <subsectionForm v-for="(subsection, index) in section.sections" 
                    :key="index"
                    v-model="section.sections[index]"
                    :number="`${number}.${index+1}`" />
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "subsectionForm",
    data: () => ({
        section: null
    }),
    props: {
        number: String,
        value: Object
    },
    methods: {
        handleInput () {
            this.$emit('input', this.section)
        },
        addSubSection() {
            this.section.sections = [ 
                ...this.section.sections,
                { title: '', content: '' }
            ]
        }
    },
    mounted() {
        this.section = { ...this.value, sections: [] }
    }
}
</script>

<style scoped>
</style>