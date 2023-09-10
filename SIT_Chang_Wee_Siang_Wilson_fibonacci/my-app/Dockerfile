FROM node:14-alpine

WORKDIR /app

EXPOSE 3000 4000 80

COPY package*.json ./
COPY . .


RUN npm run build

CMD [ "npm", "start"]