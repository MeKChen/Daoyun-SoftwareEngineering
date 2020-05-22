const IS_PROD = ['production', 'prod'].includes(process.env.NODE_ENV);
module.exports = {
    outputDir: process.env.outputDir,
    publicPath: './',
    assetsDir: 'static'
    // devServer: {
    //     // overlay: {
    //     //  warnings: true,
    //     //  errors: true
    //     // },
    //     open: IS_PROD,
    //     host: '0.0.0.0',
    //     port: 8000,
    //     https: false,
    //     hotOnly: false,
    //     proxy: {
    //         '/api': {
    //             target: process.env.VUE_APP_BASE_API || 'http://127.0.0.1:8080',
    //             changeOrigin: true
    //         }
    //     }
    // }
}
