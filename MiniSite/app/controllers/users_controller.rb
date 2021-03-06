class UsersController < ApplicationController
  before_filter :authenticate, :only => [:edit, :update]
  before_filter :correct_user, :only => [:edit, :update]

  def show
    @user = User.find(params[:id])
    @titre = @user.name
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
      redirect_to @user
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

  private

  def authenticate
    deny_access unless signed_in?
  end

  def correct_user
    @user = User.find(params[:id])
    redirect_to(root_path) unless current_user?(@user)
  end
end
