Bu projede, Kotlin kullanarak Retrofit ve Free Meal API ile başarılı bir yemek tarifi uygulaması geliştirdim. Uygulama, internetten veri çekip kullanıcıya çeşitli yemek tarifleri sunmaktadır. JSON formatındaki veriler, Free Meal API'den alınarak uygulamaya entegre edilmiştir. Bu verileri internetten çekmek ve kullanıcıya sunmak için Retrofit kütüphanesini kullandım.

Proje Özellikleri:

Retrofit ile Veri Çekme: İnternetten JSON formatında verileri çekip uygulama içinde düzenli bir şekilde listeleme.

RecyclerView Kullanımı: İnternetten çekilen verileri arayüzde düzenli bir şekilde listelemek için RecyclerView bileşeni kullanıldı.

Adapter ve Interface: Verilerin doğru şekilde gösterimi için Adapter ve Interface yapıları kullanıldı.

Uygulama Sayfaları:

Anasayfa: Bu sayfada, "beef", "chicken", "dessert", "pasta", "breakfast" gibi farklı kategoriler listelenir. Kullanıcılar, bu kategorilere tıklayarak seçim yapabilirler.

Kategori Detayı: Seçilen kategoriye ait yemeklerin listelendiği sayfa. Kullanıcılar, belirli bir kategorinin yemek çeşitlerini görüntüleyebilir.

Yemek Detayı: Kullanıcının seçtiği bir yemeğin detaylarının (yemek adı, malzemeler, tarif) görüntülendiği sayfa.

Geliştirme Süreci:

İlk olarak, Free Meal API'den kategori listesini çekerek Ana sayfada görüntüledim. Kategori listesi, RecyclerView ve Adapter kullanılarak düzenlendi. XML tasarımında, kategori adlarını gösteren iki TextView ve ilgili görseli gösteren bir ImageView kullandım. Bu tasarım öğeleri, Adapter ile verilerle eşleştirilerek sayfada görüntülendi.

İkinci aşamada, kullanıcının seçtiği kategoriye ait yemek çeşitlerinin listelendiği ikinci sayfayı geliştirdim. Bu sayfada da Retrofit ile Free Meal API'den veri çekme işlemi gerçekleştirildi. RecyclerView ve Adapter ile yemek çeşitlerini düzenli bir liste halinde kullanıcıya sundum. Sayfa geçişleri için Intents kullanarak, kullanıcıların seçtikleri kategoriye göre ilgili yemek detaylarını görmelerini sağladım.
