import PostForm from "@/src/features/posts/ui/PostForm";
import {getAdminPostById} from "@/src/features/posts/api/getPost";
import {updatePost} from "@/src/features/posts/api";
import {updatePostAction} from "@/src/features/backoffice/posts/actions";
import {getCategories} from "@/src/features/categories/api";

type Props = {
    params: Promise<{
        slug: string;
    }>
}

export default async function EditPostPage({params}:Props) {

    const {slug} = await params;
    const post = await getAdminPostById(Number(slug));
    const categories = await getCategories();

    const action = updatePostAction.bind(null, post.id);

    return <PostForm initialTitle={post.title} initialContent={post.content} initialCategories={categories} initialCategoryIds={post.categoryIds} action={action}/>

}