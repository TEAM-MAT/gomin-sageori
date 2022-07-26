const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = app => {
  app.use(
    createProxyMiddleware('/api', {
      // domain
      target: 'http://3.39.97.45',
      changeOrigin: true,
    }),
  );
};
