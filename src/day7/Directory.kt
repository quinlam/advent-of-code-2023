package day7

abstract class Directory(val name: String) {
    protected var fileSize: Int = 0

    abstract fun addFile(file: Int)

    abstract fun exit(): Directory

    fun getSize(): Int {
        return fileSize
    }
}
