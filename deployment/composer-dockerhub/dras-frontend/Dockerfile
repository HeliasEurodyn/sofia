FROM eurodynrnd/dras-frontend:latest

ARG BACKEND_URL
RUN echo "window['backend_url'] = '"${BACKEND_URL}"';" >> /usr/share/nginx/html/assets/config.js
