SiteProjets3::Application.routes.draw do

  get "trimesters/destroy"

  get "semesters/destroy"

  resources :teachers
  resources :students
  resources :uploads

  get 'sessions/add_user'
  get 'annees/publier'
  get 'projects/publier'
  get 'projects/horsligne'
  get 'annees/horsligne'
  get 'sessions/list_users'
  post 'sessions/delete_user'

  resources :annees do
    resources :projects
  end

  resources :users
  resources :sessions  do
    get :add_user, :on => :member
  end

  match '/home', :to => 'home#index'
  match '/home/course', :to => 'home#course'
  match '/home/format', :to => 'home#format'
  match '/home/project', :to => 'home#project'
  match '/login', :to =>  'sessions#new'
  match '/logout', :to => 'sessions#logout'
  match '/signup',  :to => 'users#new'
  match '/students/:id/projects', :to => 'students#projects'
  match '/teachers/:id/projects', :to => 'teachers#projects'
  match '/students/:id/projects/:id/project_add', :to => 'students#project_add', :as => :project_add
  match '/students/:id/projects/:id/project_remove', :to => 'students#project_remove', :as => :project_remove_student
  match '/teachers/:id/projects/:id/project_remove', :to => 'teachers#project_remove', :as => :project_remove_teacher
  match '/teachers/:id/projects/:id/project_add', :to => 'teachers#project_add', :as => :project_add_teacher
  match '/annees/:annee_id/projects/:id/delete', :to => 'projects#destroy', :as => :project_delete

  root :to => 'home#index'

  # The priority is based upon order of creation:
  # first created -> highest priority.

  # Sample of regular route:
  #   match 'products/:id' => 'catalog#view'
  # Keep in mind you can assign values other than :controller and :action

  # Sample of named route:
  #   match 'products/:id/purchase' => 'catalog#purchase', :as => :purchase
  # This route can be invoked with purchase_url(:id => product.id)

  # Sample resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Sample resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Sample resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Sample resource route with more complex sub-resources
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', :on => :collection
  #     end
  #   end

  # Sample resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end

  # You can have the root of your site routed with "root"
  # just remember to delete public/index.html.
  # root :to => 'welcome#index'

  # See how all your routes lay out with "rake routes"

  # This is a legacy wild controller route that's not recommended for RESTful applications.
  # Note: This route will make all actions in every controller accessible via GET requests.
  # match ':controller(/:action(/:id))(.:format)'
end
