package iii_conventions

import util.TODO


class Invokable(private val instances: Int = 0) {
    operator fun invoke(): Invokable = Invokable(instances + 1)

    fun getNumberOfInvocations() = instances
}

fun todoTask31(): Nothing = TODO(
        """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
        references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}
