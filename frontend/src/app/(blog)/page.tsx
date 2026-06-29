import PostCard from "@/src/app/(blog)/_components/PostCard";
import {getPosts} from "@/src/features/post/api";

export default async function Blog() {

    const posts = await getPosts()

    return (
        <main className="flex flex-col items-center justify-center p-8 bg-gray-50">
            {/*최상단*/}
            <section className="w-full flex items-center justify-center max-w-7xl">
                <div className="flex-1">
                    <div
                        className="inline-block px-4 py-2 bg-slate-100 rounded-2xl text-sm font-medium text-slate-500 mb-6 transition-colors duration-300">
                        👋 환영합니다
                    </div>
                    <h1 className="text-[clamp(2.5rem,5vw,3.5rem)] font-semibold leading-tight mb-6 transition-colors duration-300">
                        생각을 기록하고<br/>
                        <span
                            className="gradient-text bg-linear-to-br from-violet-500 to-fuchsia-500 bg-clip-text text-transparent">경험을 공유합니다</span>
                    </h1>
                    <p className="text-lg text-gray-600 leading-7 mb-10 transition-colors duration-300">
                        기술, 일상, 그리고 다양한 생각들을 담은 개인 블로그입니다.
                        함께 성장하고 배워가는 공간이 되길 바랍니다.
                    </p>
                    <div className="flex gap-6 md:gap-10">
                        <div className="flex flex-col gap-2">
                          <span
                              className="text-3xl font-bold bg-linear-to-br from-violet-500 to-fuchsia-500 bg-clip-text text-transparent">{posts.length}</span>
                            <span className="text-sm text-gray-600">게시글</span>
                        </div>
                        <div className="stat flex flex-col gap-2">
                          <span
                              className="stat-number text-3xl font-bold bg-linear-to-br from-violet-500 to-fuchsia-500 bg-clip-text text-transparent"
                              id="totalCategories">3</span>
                            <span className="stat-label text-sm text-gray-600">카테고리</span>
                        </div>
                        <div className="stat flex flex-col gap-2">
                          <span
                              className="stat-number text-3xl font-bold bg-linear-to-br from-violet-500 to-fuchsia-500 bg-clip-text text-transparent"
                              id="totalTags">0</span>
                            <span className="stat-label text-sm text-gray-600">태그</span>
                        </div>
                    </div>
                </div>
                <div className="relative w-100 h-100 hidden lg:block">
                    <div
                        className="absolute px-6 py-4 bg-white rounded-xl shadow-xl flex items-center gap-3 animate-bounce [animation-duration:6s] top-20 left-10 transition-colors duration-300">
                        <span className="card-emoji text-2xl">💡</span>
                        <span
                            className="card-text text-gray-600 font-semibold transition-colors duration-300">아이디어</span>
                    </div>
                    <div
                        className="absolute px-6 py-4 bg-white rounded-xl shadow-xl flex items-center gap-3 animate-bounce [animation-duration:6s] [animation-delay:-2s] top-50 right-5 transition-colors duration-300">
                        <span className="card-emoji text-2xl">🚀</span>
                        <span className="card-text text-gray-600 font-semibold transition-colors duration-300">성장</span>
                    </div>
                    <div
                        className="absolute px-6 py-4 bg-white rounded-xl shadow-xl flex items-center gap-3 animate-bounce [animation-duration:6s] [animation-delay:-4s] bottom-15 left-20 transition-colors duration-300">
                        <span className="card-emoji text-2xl">✨</span>
                        <span className="card-text text-gray-600 font-semibold transition-colors duration-300">영감</span>
                    </div>
                </div>
            </section>
            {/*태그*/}
            <section className="w-full max-w-7xl">
                <div className="flex flex-wrap gap-2">
                    <button
                        className="px-4 py-2 bg-white border border-gray-300 rounded-3xl text-sm text-gray-600 whitespace-nowrap transition-all duration-150 ease-in hover:bg-slate-100 cursor-pointer">전체
                    </button>
                    <button
                        className="px-4 py-2 bg-indigo-400 border border-gray-300 rounded-3xl text-sm text-white whitespace-nowrap transition-all duration-150 ease-in cursor-pointer">선택
                    </button>
                </div>
            </section>
            {/*  글 목록  */}
            <section className="w-full max-w-7xl mt-4">
                <div id="postsGrid"
                     className="grid grid-cols-1 md:grid-cols-[repeat(auto-fill,minmax(340px,1fr))] gap-6">
                    {posts.map((post) => (
                        <PostCard key={post.id} id={post.id} title={post.title} content={post.content} createdAt={post.createdAt} thumbnailUrl={post.thumbnailUrl} />
                    ))}
                </div>
            </section>
        </main>
    );
}
