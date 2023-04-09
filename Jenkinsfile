node {
  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/nolancon/CarApp.git'
  }

  stage("Compilation") {
    sudo sh "./compile.sh"
  }
}