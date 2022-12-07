package day7

class ChildDirectory(
    name: String,
    private val parent: Directory
) : Directory(name) {
    override fun addFile(file: Int) {
        fileSize += file
        parent.addFile(file)
    }

    override fun exit(): Directory {
        return parent
    }
}
