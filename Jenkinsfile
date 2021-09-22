def remote = [:]
remote.name = 'rita'
remote.host = 'cityscape-rita-server.eurodyn.com'
remote.allowAnyHosts = true
node
{
    stage 'Clone the project'
    git branch: 'master',
    url: 'https://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@github.com/HeliasEurodyn/sofia.git'
    dir('')
    {
        stage("Compile")
        {
            sh "chmod +x mvnw"
            sh "./mvnw clean install -DskipTests"
        }
        stage("Run Unit Tests")
        {
            try {
                sh "./mvnw test"
            } catch(err) {
                throw err
            }
        }
        stage("Deploy")
        {
            withCredentials([usernamePassword(credentialsId: 'rita', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')])
            {
                remote.user = USERNAME
                remote.password = PASSWORD
                sshCommand remote: remote, command: "docker-compose down -f /root/dev-rita-docker/docker-compose.yml"
                sshRemove remote: remote, path: '/root/dev-rita-docker/rita-backend/sofia-0.0.1.jar'
                sshPut remote: remote, from: './target/sofia-0.0.1.jar', into: '/root/dev-rita-docker/rita-backend/'
                sshCommand remote: remote, command: "docker-compose up -f /root/dev-rita-docker/docker-compose.yml -build -d"
            }
        }
    }
}
