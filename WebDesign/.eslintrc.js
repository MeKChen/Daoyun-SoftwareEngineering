module.exports = {
    root: true,
    env: {
        node: true
    },
    'extends': [
        'plugin:vue/essential',
        '@vue/standard'
    ],
    rules: {
        'no-console': 'off',
        'no-debugger': 'off',
        'no-unused-vars': 'off',
        'vue/require-prop-type-constructor': 'warning',
        'vue/require-valid-default-prop': 'warning',
        'vue/no-unused-vars': 'off',
        'vue/singleline-html-element-content-newline':'off',
        'vue/name-property-casing':'off',
        'vue/max-attributes-per-line':'off',
        'vue/html-closing-bracket-spacing':'off',
        'vue/no-side-effects-in-computed-properties':'off',
        indent:'off',
        yoda:'off',
        semi:'off',
        'object-curly-spacing':'off'
    },
    parserOptions: {
        parser: 'babel-eslint'
    }
}
