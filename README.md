<h3 align="center"> FINAL Project Matrix</h3>
<br />
<h4 align="center">
  <p>Final project from Matrix College. Montreal-CANADA.</p>
  <p>Consist in fix some bugs in the Easybank Application  and create tests cases and finally automatise.</p>
  <p>The application it's in folder <strong> SITE </strong></p>
  <p>The Selenium project it's in <strong> automationEasy </strong></p>
<br />

### Commands
```bash
# Clone
$ git clone https://github.com/mariuo/finalProjectMatrix

# Run docker-composegit s
$ docker-compose up -d

# Run scrip mysql-docker.sh to seed the database
$ ./mysql-docker.sh

# Command to down all containers
$ docker-compose down

# Command to down and remove all containers/images
$ docker-compose down --volumes --rmi all

# Command to run Tests in Terminal.
$ cd automationEasy
$ mvn test

```
### http://localhost:8000 - app
### http://localhost:8081 - phpmyadmin

