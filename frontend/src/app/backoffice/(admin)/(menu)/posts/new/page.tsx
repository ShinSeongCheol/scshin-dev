import PostForm from "@/src/features/posts/ui/PostForm";
import {createPostAction} from "@/src/features/backoffice/posts/actions";
import {getCategories} from "@/src/features/categories/api";

export default async function NewPostPage() {

    const categories = await getCategories();

    return (
        <PostForm
            initialTitle={""}
            initialContent={""}
            initialCategories={categories}
            initialCategoryIds={[]}
            action={createPostAction}
        />
    )
}