package day7

class RootDirectory(name: String) : Directory(name) {
    override fun addFile(file: Int) {
        fileSize += file
    }

    override fun exit(): Directory {
        return this
    }
}
