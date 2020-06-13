const IS_PROD = ['production', 'prod'].includes(process.env.NODE_ENV);
module.exports = {
    outputDir: process.env.outputDir, 
    publicPath: './',
    assetsDir: 'static',
    lintOnSave: false
}
