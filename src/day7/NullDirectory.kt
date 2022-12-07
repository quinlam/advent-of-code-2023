package day7

class NullDirectory : Directory("") {
    override fun addFile(file: Int) {
        throw RuntimeException("Can not add to a null directory")
    }

    override fun exit(): Directory {
        throw RuntimeException("Not implemented")
    }
}
