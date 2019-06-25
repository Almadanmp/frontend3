export default {

  items: [
    {
      name: 'Home page',
      url: '/homepage',
      icon: 'icon-speedometer',
    },
    {
      title: true,
      name: 'SMART',
      wrapper: {            // optional wrapper object
        element: '',        // required valid HTML5 element tag
        attributes: {}        // optional valid JS object with JS API naming ex: { className: "my-class", style: { fontFamily: "Verdana" }, id: "my-id"}
      },
      class: ''             // optional class names space delimited list for title item ex: "text-center"
    },
    {
      name: 'Area',
      url: '/area',
      icon: 'icon-globe',
    },
    {
      name: 'House',
      url: '/house/configuration',
      icon: 'icon-home',
    },
    {
      name: 'Room',
      url: '/room/configuration',
      icon: 'fa fa-bed fa-lg',
    },
    {
      name: 'Energy',
      url: '/energy',
      icon: 'icon-energy\n',
    },
    {
      name: 'Sensor',
      url: '/sensor',
      icon: 'icon-speedometer\n',
    },
    {
      name: 'Data Import',
      url: '/configuration',
      icon: 'icon-cloud-upload',
    },

    {
      divider: true,
    },
    {
      title: true,
      name: 'Extras',
    },
    {
      name: 'About',
      url: '/about',
      icon: 'icon-star',
    },
    {
      name: 'The Team',
      url: '/team',
      icon: 'icon-star',
    },
  ],
};
