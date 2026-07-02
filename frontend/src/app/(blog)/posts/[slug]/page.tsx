import {Metadata} from "next";
import {getPost} from "@/src/features/posts/api";

type Props = {
    params: Promise<{
        slug: string;
    }>
}

export async function generateMetadata({params}:Props): Promise<Metadata> {
    const {slug} = await params;
    const post = await getPost(Number(slug));

    return {
        title: post.title ,
        description: post.title,
        openGraph: {
            title: post.title,
            description: `${post.title} 상세 페이지입니다.`,
            type: "article",
            images: [
                {
                    url: post.thumbnailUrl || "/default-og.png",
                    width: 1200,
                    height: 630,
                    alt: post.title,
                },
            ],
        },
    }
}

export default async function Post({params} : Props) {
    const {slug} = await params;
    const post = await getPost(Number(slug))

    return (
        <main className="flex justify-center sm:p-4 min-h-dvh bg-gray-50">
            <div className="w-full h-full lg:w-7xl bg-white flex flex-col gap-4 rounded-xl shadow-xl p-4">
                <div className="prose max-w-none w-full">
                    <h1 className="p-4 border-b border-b-gray-300">{post.title}</h1>
                </div>
                <div
                    id="content"
                    className="prose max-w-none p-4 prose-img:max-w-[50%] prose-img:w-auto prose-img:mx-auto"
                    dangerouslySetInnerHTML={{ __html: post.content }}
                >
                </div>
            </div>
        </main>
    )
}