# UsoamicKtJs
KotlinJS library for interaction with Usoamic contract.

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

## How To Build Only Jar File
1. Gradle
2. usoamicktjs
3. Tasks
4. Build
5. JsJar (NOT build)