# UsoamicKtJs
KotlinJS library for interaction with Usoamic contract
Move library to libs folder.

## Gradle Setup
```gradle
repositories {
    flatDir {
        dirs 'libs'
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
}
```
