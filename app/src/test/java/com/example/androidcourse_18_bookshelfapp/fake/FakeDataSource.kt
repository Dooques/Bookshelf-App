package com.example.androidcourse_18_bookshelfapp.fake

import com.example.androidcourse_18_bookshelfapp.model.BookshelfModel

object FakeDataSource {
//    const val searchTerms = "final fantasy"
    private const val ID_ONE = "idOne"

//    val categories = listOf("final fantasy")

    private const val SMALL = "http://books.google.com/books/publisher/content?id=ubERDAAAQBAJ&printsec=frontcover&img=1&zoom=2&edge=curl&imgtk=AFLRE73Q7fFBH-ZUboSMZQs5fhqwvVZLsU1m__4XpIyuH8dHVEcgfoyciGHpLcsx-zytwm-bX3Iqn3zo9Uz0iEAOHKQ9j6pXp3UOp9X9EPLJ59iDm02JQZP3j0PhMWXjVPr4ea_JUxPO&source=gbs_api"
//    const val SMALL_PROC = "https://books.google.com/books/publisher/content?id=ubERDAAAQBAJ&printsec=frontcover&img=1&zoom=2&edge=curl&imgtk=AFLRE73Q7fFBH-ZUboSMZQs5fhqwvVZLsU1m__4XpIyuH8dHVEcgfoyciGHpLcsx-zytwm-bX3Iqn3zo9Uz0iEAOHKQ9j6pXp3UOp9X9EPLJ59iDm02JQZP3j0PhMWXjVPr4ea_JUxPO&source=gbs_api"

    private const val MEDIUM = "http://books.google.com/books/publisher/content?id=ubERDAAAQBAJ&printsec=frontcover&img=1&zoom=3&edge=curl&imgtk=AFLRE72OGLe3lDrDkzkr_pKQ7L0eVKiq3QLwO1152Qc3gjU8p73w0d45EcFqiFQ_fHCvgeUF1vx1cN8Z6PUwGkg7bjLs-qScZSMNp8ABniegRIuBfXPDRKmXcLrELaXlYckyA_c-H3eR&source=gbs_api"
//    const val MEDIUM_PROC = "https://books.google.com/books/publisher/content?id=ubERDAAAQBAJ&printsec=frontcover&img=1&zoom=3&edge=curl&imgtk=AFLRE72OGLe3lDrDkzkr_pKQ7L0eVKiq3QLwO1152Qc3gjU8p73w0d45EcFqiFQ_fHCvgeUF1vx1cN8Z6PUwGkg7bjLs-qScZSMNp8ABniegRIuBfXPDRKmXcLrELaXlYckyA_c-H3eR&source=gbs_api"

    private const val LARGE = "http://books.google.com/books/publisher/content?id=ubERDAAAQBAJ&printsec=frontcover&img=1&zoom=4&edge=curl&imgtk=AFLRE71XQQNfQSzd1FBwRBuWuZe1P2ELwrrgMhoraD41ZIOxMMYCzUWiF5hEsqlFO_ExYdMFN3SRPWieKSSUpUxdRMTW29AwVMjFg4Tb6eXOxpmE0uyXz3TcfTU5O4rSvsgJmhom_NBq&source=gbs_api"
//    const val LARGE_PROC = "https://books.google.com/books/publisher/content?id=ubERDAAAQBAJ&printsec=frontcover&img=1&zoom=4&edge=curl&imgtk=AFLRE71XQQNfQSzd1FBwRBuWuZe1P2ELwrrgMhoraD41ZIOxMMYCzUWiF5hEsqlFO_ExYdMFN3SRPWieKSSUpUxdRMTW29AwVMjFg4Tb6eXOxpmE0uyXz3TcfTU5O4rSvsgJmhom_NBq&source=gbs_api"

    private const val DESC = "<b>A hardcover volume that showcases the intriguing evolution of pixel art from the <i>Final Fantasy</i> series!</b><br><br>Containing detailed sprite sheets that showcase the pixel composition of <i>Final Fantasy</i>'s beloved characters, maps of <i>Final Fantasy</i>'s most popular highlighting tools used by the developers, and a special interview with Kazuko Shibuya, the character pixel artist for the <i>Final Fantasy series</i>, <i>FF Dot</i> is a one of a kind product that immerses readers into an iconic aspect of the <i>Final Fantasy</i> experience. <br><br>Dark Horse Books is proud to collaborate with Square Enix to bring fans <i>FF Dot: The Pixel Art of Final Fantasy</i>, translated into English for the first time. This localization of the original Japanese publication holds nearly 300 pages of colorful pixel art, and is an invaluable addition to any <i>Final Fantasy</i> fan's collection."
//    const val DESC_CLEAN = "A hardcover volume that showcases the intriguing evolution of pixel art from the Final Fantasy series! Containing detailed sprite sheets that showcase the pixel composition of Final Fantasy's beloved characters, maps of Final Fantasy's most popular highlighting tools used by the developers, and a special interview with Kazuko Shibuya, the character pixel artist for the Final Fantasy series, FF Dot is a one of a kind product that immerses readers into an iconic aspect of the Final Fantasy experience. Dark Horse Books is proud to collaborate with Square Enix to bring fans FF Dot: The Pixel Art of Final Fantasy, translated into English for the first time. This localization of the original Japanese publication holds nearly 300 pages of colorful pixel art, and is an invaluable addition to any Final Fantasy fan's collection."

    val bookList = BookshelfModel.BookList(
        totalItems = 1,
        items = listOf(
            BookshelfModel.VolumeId(
                id = idOne
            )
        )
    )

    val volume: BookshelfModel.Volume = BookshelfModel.Volume(
        id = idOne,
        volumeInfo = BookshelfModel.VolumeInfo(
            imageLinks = BookshelfModel.ImageLink(
                small = SMALL,
                medium = MEDIUM,
                large = LARGE
            ),
            description = DESC
        )
    )
}