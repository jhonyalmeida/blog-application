<template>
    <section class="mt-3 mb-3">
        <h3 v-if="number.length === 1">{{ `${number}. ${section.title}` }}</h3>
        <h4 v-else>{{ `${number}. ${section.title}` }}</h4>
        <p>{{ section.content }}</p>
        <div class="mt-3 mb-3">
            <subsection v-for="(subsection, index) in section.sections" 
                :key="index"
                v-show="subsection.title"
                :number="`${number}.${index+1}`" 
                :section="subsection" />
        </div>
    </section>
</template>

<script>
export default {
    name: "subsection",
    props: {
        number: String,
        section: Object
    }
}
</script>

<style scoped>
</style>