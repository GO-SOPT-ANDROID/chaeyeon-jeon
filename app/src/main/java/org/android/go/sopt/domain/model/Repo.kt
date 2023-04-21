package org.android.go.sopt.domain.model

data class Repo(
    val id: Int,
    val image: String?, // TODO: 이미지 url null로 받아지는 오류 해결ㅠㅠ
    val name: String,
    val owner: String,
)
