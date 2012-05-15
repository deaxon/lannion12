class UsersController < ApplicationController
  before_filter :authenticate, :authorize, :except => [:new, :create]
  #before_filter :correct_user, :only => [:edit, :update]

  def show
    @user = User.find(params[:id])
    @titre = @user.name
  end

  def index
    @users = User.all
  end

  def new
    @user = User.new
    @titre = "Register"
  end

  def create
    @user = User.new(params[:user])
    if @user.save
      sign_in @user
      flash[:success] = "Welcome on the application Example !"
      redirect_to(:controller => "sessions", :action => "index")
    else
      @titre = "Sign up"
      render 'new'
    end
  end

  def edit
    @titre = "Edit profile"
  end

  def update
    @user = User.find(params[:id])
    if @user.update_attributes(params[:user])
      flash[:success] = "Profile update."
      redirect_to @user
    else
      @titre = "Edit profile"
      render 'edit'
    end
  end

  def destroy
    @user = User.find(params[:id])
    @user.destroy
     redirect_to(:controller => 'sessions', :action => 'list_users')
  end

  private

  def authenticate
    deny_access unless signed_in?
  end

  def correct_user
    @user = User.find(params[:id])
    redirect_to(root_path) unless current_user?(@user)
  end
end