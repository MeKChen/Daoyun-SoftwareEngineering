import store from '@/store'

const {body} = document
const WIDTH = 768
const RATIO = 3
const PAD_WIDTH = 1024

export default {
  watch: {
    $route () {
      if (this.device === 'mobile' && this.sidebar.opened) {
        store.dispatch('closeSideBar', {withoutAnimation: false})
      }
    }
  },
  beforeMount () { 
    window.addEventListener('resize', this.resizeHandler)
  },
  mounted () {
    const deviceType = this.deviceType()
    if (deviceType === 'mobile') {
      store.dispatch('toggleDevice', 'mobile')
      store.dispatch('closeSideBar', {withoutAnimation: true})
    }
  },
  methods: {
    deviceType () {
      const rect = body.getBoundingClientRect();
      console.log(rect.width);
      if (rect.width - RATIO < WIDTH) return 'mobile';
      if (rect.width - RATIO < PAD_WIDTH) return 'pad';
      return 'desktop';
    },

    resizeHandler () {
      if (!document.hidden) {
        const deviceType = this.deviceType()
        store.dispatch('toggleDevice', deviceType)
        if (deviceType === 'mobile') {
          store.dispatch('closeSideBar', {withoutAnimation: true})
        }
      }
    }
  }
}
